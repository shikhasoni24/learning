$(document).ready(function(){


$("#xpathquery").on("click",function(e){debugger;
        e.preventDefault();
    $.ajax({
        type:"GET",
        datatype:"json",
        url:"/bin/search/xpathquery",
        success:function(data){
                        console.log(data);

          var a=$.parseJSON(data);
            console.log(a);
            $("#Bumrah").html(data);

        },
        error:function(e){
              console.log(e);
        }
    });
    })
})

