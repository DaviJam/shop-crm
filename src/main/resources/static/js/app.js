
$(document).ready(function() {
    $(".form").on("submit", function(event){
        var data = Object.fromEntries(new FormData(event.target).entries());
        var d = $("input[class='passwordconfirm']")[0];
        if(data.password !== d.value){
            $(d.parentNode).addClass("error");
            $(".message").show();
            setTimeout(()=>{
                $('.message').animate({"opacity":0},3000,"linear",()=>{
                    $(d.parentNode).removeClass('error');
                    $(".message").hide();
                    $('.message').css("opacity",1);
                });
            }, 3000);
            event.preventDefault();
        }
    });
});