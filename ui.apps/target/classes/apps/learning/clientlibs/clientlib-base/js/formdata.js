/*window.onload=function(e){debugger;
    alert("hello");
    e.preventDefault();

    $.ajax({

        data:{ fname:$("#fname").val(),
               lname:$("#lname").val()
             },
        type:"GET",
        datatype:"json",
        url:"/content/learning/en/demo.html",
        success:function(data){
          var a=$.parseJSON(data);
            console.log("data" +a.datavalue);
            $("#sachin").append(a.datavalue);

        },
        error:function(e){
              console.log("error"  +e);
        }
    });

} ;*/

