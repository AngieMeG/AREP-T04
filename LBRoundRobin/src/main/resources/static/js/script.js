webClient = (function(){
    var messages;
    var url = window.location.href + "/messages";

    function postMessage(newMessage){
        axios.post(url, newMessage)
        .then(res => {show();})
    }

    function show(){
        axios.get(url).then(res => {
            console.log(res.data);
            $(".table-wrapper").empty();
            console.log($(".table-wrapper"));
            for (let i = 0; i < res.data.length; i++) {
                $(".table-wrapper").append(
                    '<div class=row-table><p class=content>'+ res.data[i].content + '</p><p class=date>'+ res.data[i].creation_date + '</p></div>'
                );
            }
        })
    }

    return{
        postMessage: postMessage
    }
})();