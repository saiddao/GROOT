// Fetch the JSON schema
fetch('ontologyshema.json')
  .then(response => response.json())
  .then(schema => {
    const form = document.createElement('form');
    const container = document.getElementById('form');
    container.appendChild(form);

    // Initialize the JSON editor
    const editor = new JSONEditor(form, {
      schema: schema
    });

    // Listen for the form's submit event
    form.addEventListener('submit', event => {
      event.preventDefault();
      const formData = editor.getValue();
      const jsonData = JSON.stringify(formData, null, 2);
      console.log(jsonData); // log the JSON data to the console
      const output = document.getElementById('data-output');
      output.textContent = jsonData; // display JSON output
      // Send the JSON data to the server using a fetch request
      fetch('https://example.com/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: jsonData
      }).then(response => {
        console.log(response); // log the server response to the console
      }).catch(error => {
        console.error(error); // log any errors to the console
      });
    });
  });