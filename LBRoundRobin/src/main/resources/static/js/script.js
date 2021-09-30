webClient = (function(){
    var messages;

    const postMessage = (newMessage) => {
        fetch("/messages",
        {
            headers: {
                "Accept": "text/plain",
                "Content-Type": "text/plain"
            },
            method: "POST",
            body: newMessage
        })
        .then(response => response.json().then(parsedJson => {
            messages = parsedJson;
            show();
        }));
    }

    const getMessages = () => {
        fetch("/messages")
        .then(response => response.json().then(parsedJson => {
            messages = parsedJson;
            show();
        }));
    }

    const show =  () => {
        $("#table-body").empty();
        console.log($("#table-body"));
        for (let i = 0; i < messages.length; i++) {
            console.log(messages[i]);
            console.log(messages[i].content);

            $("#table-body").append(
                "<tr><td>" + messages[i].content + "</td><td>" + messages[i].creation_date+"</td></tr>"
            );
        }
    }

    return{
        postMessage: postMessage,
        getMessages: getMessages
    }
})();