
//Funcion al dar me gusta
function darLike(postId, userId) {
    fetch(`darLike?postId=${postId}&userId=${userId}`, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Actualiza la interfaz de usuario según la respuesta del servidor
            const likeIcon = document.querySelector(`.post-actions i[onclick="darLike(${postId}, ${userId})"]`);
            const likeCountElement = likeIcon.nextElementSibling; // Obtiene el siguiente elemento (en este caso, el <span>)

            if (data.liked) {
                likeIcon.classList.remove('fa-regular');
                likeIcon.classList.add('fa-solid');
            } else {
                likeIcon.classList.remove('fa-solid');
                likeIcon.classList.add('fa-regular');
            }

            likeCountElement.textContent = data.likeCount;
        })
        .catch(error => console.error('Error al realizar la solicitud fetch:', error));
}

//Funcion al guardar la publicacion
function guardarPost(postId, userId) {
    fetch(`guardarPost?postId=${postId}&userId=${userId}`, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Actualiza la interfaz de usuario según la respuesta del servidor
            const saveIcon = document.querySelector(`.post-actions i[onclick="guardarPost(${postId}, ${userId})"]`);
            const saveCountElement = saveIcon.nextElementSibling; // Obtiene el siguiente elemento (en este caso, el <span>)

            if (data.saved) {
                saveIcon.classList.remove('fa-regular');
                saveIcon.classList.add('fa-solid');
            } else {
                saveIcon.classList.remove('fa-solid');
                saveIcon.classList.add('fa-regular');
            }

            saveCountElement.textContent = data.saveCount;
        })
        .catch(error => console.error('Error al realizar la solicitud fetch:', error));
}

//Funcion para borrar publicación
function borrarPost(postId) {
    fetch(`borrarPost?postId=${postId}`, {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            // Verificamos el estado de la respuesta
            if (data.respuesta === "ok") {
                // Encuentra el contenedor del post por su ID y elimínalo
                var postContainer = document.querySelector(`div[data-id="${postId}"]`);
                if (postContainer) {
                    postContainer.remove();
                } else {
                    console.error("No se encontró el contenedor del post");
                }
            } else {
                // Algo salió mal
                console.error("Error al eliminar la publicación");
            }
        })
        .catch(error => console.error('Error al realizar la solicitud fetch:', error));
}

