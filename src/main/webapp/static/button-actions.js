(() => {
    const refreshPage = () => {
        window.location.reload();
    }

    const refreshButton = document.getElementById('refresh-button');
    refreshButton.addEventListener('click', refreshPage);

    const USER_INPUT_FIELDS = {
        USERNAME_INPUT: "username-input",
        NAME_INPUT: "name-input",
        EMAIL_INPUT: "email-input",
        PASSWORD_INPUT: "password-input",
    };

    const modalRoot = document.getElementById('modal-root');

    const openUserModal = ({ userDTO, onSave }) => {
        const shouldUpdate = Boolean(userDTO);

        modalRoot.innerHTML = `
        <form id="user-form">
            ${shouldUpdate ? '' : `
                <div class="user-form-input-field">
                    <label for="${USER_INPUT_FIELDS.USERNAME_INPUT}">Username</label>
                    <input id="${USER_INPUT_FIELDS.USERNAME_INPUT}" />
                </div>
            `}
            <div class="user-form-input-field">
                <label for="${USER_INPUT_FIELDS.NAME_INPUT}">Name</label>
                <input id="${USER_INPUT_FIELDS.NAME_INPUT}" value="${userDTO?.name ?? ''}" />
            </div>
            <div class="user-form-input-field">
                <label for="${USER_INPUT_FIELDS.EMAIL_INPUT}">Email</label>
                <input id="${USER_INPUT_FIELDS.EMAIL_INPUT}" value="${userDTO?.email ?? ''}" />
            </div>
            <div class="user-form-input-field">
                <label for="${USER_INPUT_FIELDS.PASSWORD_INPUT}">Password</label>
                <input id="${USER_INPUT_FIELDS.PASSWORD_INPUT}" type="password" />
            </div>
            <div class="user-form-input-field" id="user-form-buttons">
                <button id="cancel-button" type="button">Cancel</button>
                <button id="save-user-button" type="button">Save</button>
            </div>
        </form>
       `;

        modalRoot.style.visibility = 'visible';

        const usernameInput = document.getElementById(USER_INPUT_FIELDS.USERNAME_INPUT);
        const nameInput = document.getElementById(USER_INPUT_FIELDS.NAME_INPUT);
        const emailInput = document.getElementById(USER_INPUT_FIELDS.EMAIL_INPUT);
        const passwordInput = document.getElementById(USER_INPUT_FIELDS.PASSWORD_INPUT);

        document
            .getElementById('cancel-button')
            .addEventListener('click', () => {
                modalRoot.style.visibility = 'hidden';
                modalRoot.innerHTML = '';
            });

        document
            .getElementById('save-user-button')
            .addEventListener('click', async () => {
                await onSave({
                    username: usernameInput?.value,
                    name: nameInput.value,
                    email: emailInput.value,
                    password: passwordInput.value
                })

                refreshPage();
            });
    }

    const addUserButton = document.getElementById('add-user-button');
    addUserButton.addEventListener('click', () => {
        const onSave = async ({ username, name, email, password }) => {
            await fetch('/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, name, email, password })
            });
        };

        openUserModal({
            onSave
        });
    });

    const editUserButtons = document.getElementsByClassName("edit-user-action")
    for(const editUserButton of editUserButtons) {
        const editUserButtonId = editUserButton.id;
        const userId = editUserButtonId.split(':')[1];

        editUserButton.addEventListener('click', async () => {
            const usersResponse = await fetch(`/api/users?id=${userId}`);
            const userDTOs = await usersResponse.json();

            const userDTO = userDTOs?.[0];
            if(!userDTO) {
                return;
            }

            const onSave = async ({ name, email, password }) => {
                await fetch('/api/users', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        id: userId,
                        name,
                        email,
                        password: password?.length ? password : undefined
                    })
                });
            };

            openUserModal({
                userDTO,
                onSave
            });
        });
    }
})();
