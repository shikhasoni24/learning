$(document).ready(function(){


$("#submitthisform").on("click",function(e){debugger;
        e.preventDefault();

var b=$("#search").val();

    $.ajax({
        data:{fname:b},
        type:"GET",
        datatype:"json",
        url:"/bin/search/paginationsearch",
        success:function(data){
                        console.log(data);

          var a=$.parseJSON(data);
            console.log(a);
			var str="<table style='width:100%'><tr><th>Title</th><th>Path</th></tr>";
            for(var i=0;i<a.results.length;i++){
				    str+="<tr><td>"+a.results[i].title+"</td><td>"+a.results[i].path+"</td></tr>";
            }
            str+="</table>";
            $("#kohli").html(str);

        },
        error:function(e){
              console.log(e);
        }
    });
    })
})

