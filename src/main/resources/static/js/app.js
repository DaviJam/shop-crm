
$(document).ready(function() {
    var message_popup = $(".message")[0];
    if(document.URL.includes("register")) {
        $(".form").on("submit", function(event){
            var data = Object.fromEntries(new FormData(event.target).entries());
            var d = $("input[class='passwordconfirm']")[0];
            if(data.password !== d.value){
                message_popup.setAttribute("action");
                event.preventDefault();
            }
        });
    }

    function message_action(){
        message_popup.show();
        setTimeout(()=>{
            message_popup.animate({"opacity":0},3000,"linear",()=>{
                message_popup.removeClass('error');
                message_popup.hide();
                message_popup.css("opacity",1);
            });
        }, 3000);
    }
});