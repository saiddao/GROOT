// function to load AC policy file and show its content in the text area
function loadPolicy() {
    var file = $("#policyFile")[0].files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        $("#policyContent").val(e.target.result);
    }
    reader.readAsText(file);
}

// function to generate access control requests and display them in a table
function generateRequests() {
    var policyContent = $("#policyContent").val();
    // code to generate requests goes here
    var requests = [
        { id: 1, request: "Request 1", oracle: "Permit", response: "Permit" },
        { id: 2, request: "Request 2", oracle: "Deny", response: "Permit" },
        { id: 3, request: "Request 3", oracle: "Deny", response: "Deny" }
    ];
    // create table rows for each request
    var tableRows = "";
    for (var i = 0; i < requests.length; i++) {
        tableRows += "<tr><td>" + requests[i].id + "</td><td>" + requests[i].request + "</td><td>" + requests[i].oracle + "</td><td></td></tr>";
    }
    $("#requestsTable tbody").html(tableRows);
}

// function to generate expected responses (oracle) for each request
function generateOracle() {
    $("#requestsTable tbody tr").each(function() {
        var request = $(this).find("td:nth-child(2)").html();
        // code to generate oracle for request goes here
        var oracle = "Permit";
        $(this).find("td:nth-child(3)").html(oracle);
    });
}

// function to evaluate actual responses for each request
function evaluateRequests() {
    $("#requestsTable tbody tr").each(function() {
        var request = $(this).find("td:nth-child(2)").html();
        // code to evaluate actual response for request goes here
        var response = "Permit";
        $(this).find("td:nth-child(4)").html(response);
    });
}