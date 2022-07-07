$("#submithotel").click(function(e){debugger;
    e.preventDefault();

    $.ajax({

        data:{ hName:$("#hotelName").val(),
               hState:$("#hotelState").val()
             },
        type:"GET",
        //datatype:"json",
        url:"/bin/hotel/data",
        success:function(data){
         // var a=$.parseJSON(data);SS
           // console.log("data" +a.datavalue);
            $("#hotelresult").append(data);

        },
        error:function(e){
              console.log("error"  +e);
        }
    });

}) ;

