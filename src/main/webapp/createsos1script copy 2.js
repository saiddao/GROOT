// Get the JSON schema from the server
// fetch('ontologyshema.json')
//   .then(response => response.json())
//   .then(schema => {
//     // Create the form based on the schema
//     const form = document.createElement('form');
//     createForm(form, schema);
//     document.body.appendChild(form);

//     // Add a submit button to the form
//     const submitButton = document.createElement('button');
//     submitButton.textContent = 'Submit';
//     form.appendChild(submitButton);

//     // Listen for the form's submit event
//     form.addEventListener('submit', event => {
//       event.preventDefault();
//       const formData = new FormData(form);
//       const data = {};
//       for (const [key, value] of formData.entries()) {
//         set(data, key.split('.'), value);
//       }
//       console.log('Form data:', data);
//     });
//   });






  fetch('ontologyshema.json')
  .then(response => response.json())
  .then(schema => {
    // Create the form based on the schema
    const form = document.createElement('form');
    createForm(form, schema);
    document.body.appendChild(form);

    // Add a submit button to the form
    const submitButton = document.createElement('button');
    submitButton.textContent = 'Submit';
    form.appendChild(submitButton);

    // Add a pre element to display JSON output
    const output = document.createElement('pre');
    document.body.appendChild(output);

    // Listen for the form's submit event
    form.addEventListener('submit', event => {
      event.preventDefault();
      const formData = new FormData(form);
      const data = {};
      for (const [key, value] of formData.entries()) {
        set(data, key.split('.'), value);
      }
      output.textContent = JSON.stringify(data, null, 2); // display JSON output
    });
  });

















// Function to create a form element based on a JSON schema
  function createForm(parentElement, schema) {
    switch (schema.type) {
      case 'object':
        for (const [key, value] of Object.entries(schema.properties)) {
          const label = document.createElement('label');
          label.textContent = key;
          parentElement.appendChild(label);
          createForm(parentElement, value);
        }
        break;
      case 'array':
        if (schema.items.type === 'object') {
          const addButton = document.createElement('button');
          addButton.textContent = `Add ${schema.title || 'Item'}`;
          parentElement.appendChild(addButton);
          const itemContainer = document.createElement('div');
          parentElement.appendChild(itemContainer);
          addButton.addEventListener('click', () => {
            const itemForm = document.createElement('fieldset');
            createForm(itemForm, schema.items);
            const deleteButton = document.createElement('button');
            deleteButton.textContent = `Delete ${schema.title || 'Item'}`;
            itemForm.appendChild(deleteButton);
            itemContainer.appendChild(itemForm);
            deleteButton.addEventListener('click', () => {
              itemContainer.removeChild(itemForm);
            });
          });
        } else {
          const input = document.createElement('input');
          input.type = 'text';
          parentElement.appendChild(input);
        }
        break;
      case 'string':
        const input = document.createElement('input');
        input.type = 'text';
        parentElement.appendChild(input);
        break;
      default:
        break;
    }
  }



// Function to create a form element based on a JSON schema
function createFormOLD(parentElement, schema) {
  switch (schema.type) {
    case 'object':
      for (const [key, value] of Object.entries(schema.properties)) {
        const label = document.createElement('label');
        label.textContent = key;
        parentElement.appendChild(label);
        createForm(parentElement, value);
      }
      break;
    case 'array':
      if (schema.items.type === 'object') {
        const addButton = document.createElement('button');
        addButton.textContent = 'Add Item';
        parentElement.appendChild(addButton);
        const itemContainer = document.createElement('div');
        parentElement.appendChild(itemContainer);
        addButton.addEventListener('click', () => {
          const itemForm = document.createElement('fieldset');
          createForm(itemForm, schema.items);
          const deleteButton = document.createElement('button');
          deleteButton.textContent = 'Delete Item';
          itemForm.appendChild(deleteButton);
          itemContainer.appendChild(itemForm);
          deleteButton.addEventListener('click', () => {
            itemContainer.removeChild(itemForm);
          });
        });
      } else {
        const input = document.createElement('input');
        input.type = 'text';
        parentElement.appendChild(input);
      }
      break;
    case 'string':
      const input = document.createElement('input');
      input.type = 'text';
      parentElement.appendChild(input);
      break;
    default:
      break;
  }
}

// Function to set a value in an object using a path array
function set(obj, path, value) {
  let currentObj = obj;
  for (let i = 0; i < path.length - 1; i++) {
    const key = path[i];
    if (!currentObj[key]) {
      currentObj[key] = {};
    }
    currentObj = currentObj[key];
  }
  currentObj[path[path.length - 1]] = value;
}
