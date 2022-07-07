$(document).ready(function(e){
e.preventDefault();
    $("#submitform").click(function(){
    var name=$("#formName").val();

	var state=$("#formState").val();
        if(name.length>8){
				$.ajax({

        data:{ formname:name,
               formstate:state
             },
        type:"GET",

        url:"/bin/data/myservlet",
        success:function(data){
          var a=data;
            console.log(a);
            $("#formresult").append(a);

        },
        error:function(e){
              console.log("error"  +e);
        }
    });


        }
})

})