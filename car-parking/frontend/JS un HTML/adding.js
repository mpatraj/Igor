
document.addEventListener('DOMContentLoaded', () => {

    // By default, submit button is disabled
    document.querySelector('#submit').disabled = true;

    // Enable button only if there is text in the input field
    document.querySelector('#task').onkeyup = () => {
        if (document.querySelector('#task').value.length > 0 && document.querySelector('#task').value.length < 7 )
            document.querySelector('#submit').disabled = false;
        else
            document.querySelector('#submit').disabled = true;
    };

    document.querySelector('#new-task').onsubmit = () => {

        // Create new item for list
        const li = document.createElement('lu');
        li.innerHTML = document.querySelector('#task').value;

        // Add new item to task list
        document.querySelector('#tasks').append(li);

        // Clear input field and disable button again
        document.querySelector('#task').value = '';
        document.querySelector('#submit').disabled = true;

        // Stop form from submitting
        return false;
    };

});