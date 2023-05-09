// get the schema from the API or from a JSON file
const schema = {
    "title": "Person",
    "type": "object",
    "required": [
      "firstName",
      "lastName"
    ],
    "properties": {
      "firstName": {
        "type": "string",
        "title": "First name"
      },
      "lastName": {
        "type": "string",
        "title": "Last name"
      },
      "age": {
        "type": "integer",
        "title": "Age"
      },
      "email": {
        "type": "string",
        "title": "Email",
        "format": "email"
      },
      "address": {
        "type": "object",
        "title": "Address",
        "properties": {
          "street": {
            "type": "string",
            "title": "Street"
          },
          "city": {
            "type": "string",
            "title": "City"
          },
          "state": {
            "type": "string",
            "title": "State"
          },
          "zip": {
            "type": "string",
            "title": "Zip"
          }
        }
      }
    }
  };
  
  // get the form container element
  const formContainer = document.getElementById("form-container");
  
  // create a function to generate the form based on the schema
  function generateForm(schema, parent) {
    const keys = Object.keys(schema.properties);
    keys.forEach((key) => {
      const property = schema.properties[key];
      const label = document.createElement("label");
      label.innerText = property.title || key;
      const input = document.createElement("input");
      input.name = key;
      input.type = property.type === "string" ? "text" : property.type;
      if (property.format === "email") {
        input.type = "email";
      }
      if (property.type === "object") {
        input.type = "fieldset";
        const fieldset = document.createElement("fieldset");
        generateForm(property, fieldset);
        input.appendChild(fieldset);
      }
      parent.appendChild(label);
      parent.appendChild(input);
    });
  }
  
  // generate the form
  generateForm(schema, formContainer);
  