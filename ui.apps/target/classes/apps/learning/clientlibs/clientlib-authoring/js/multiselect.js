$(document).on("ready",function(e){debugger;
var arr=["aam","amrud","aaalu","pyaz"];
    var x="<coral-selectlist multiple>";

    for(var i=0;i<arr.length;i++){
       x+="<coral-selectlist-item>"+arr[i]+"</coral-selectlist-item>";
        }
x+="</coral-selectlist>";
   $("section[data-cls='multivalue']").append("<div id='multidrop'> </div>");
    $("#multidrop").append("<input type='text' id='textvalue'>");
$("#multidrop").append(x);

  //  $("section[data-cls='multivalue']").append('<div class="coral-Form-fieldwrapper" id="regions" style="display: inline-block;">');
})