async function post(message){
    const res = await fetch("/messages", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: newMessage
    });
    console.log("response " + res);
    const data = await res.json();
    console.log("Data " + data);
    showRows(data);
}

function showRows(data){
    $("#table-body").empty();
        for (let i = 0; i < data.length; i++) {
            console.log(data[i]);
            console.log(data[i].content);
            $("#table-body").append(
                "<tr><td>" + data[i].content + "</td><td>" + data[i].creation_date+"</td></tr>"
            );
        }
}