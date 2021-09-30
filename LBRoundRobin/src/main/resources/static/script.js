let webClient = function(){
    var messages = [];

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
        .then(function(res){console.log(res)})
    }
}