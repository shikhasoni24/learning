$(document).ready(function(){

    $("#weather").on("click",function(e){
        e.preventDefault();
        var cityname=$("#city").val();
        $.ajax({
            url:"/bin/data/weather",
            data:{nameofcity:cityname},	
            type:"GET",
            datatype:"json",
            success:function(data){
                var v=JSON.parse(data);
                console.log(v.user);
                var va=JSON.parse(v.user);
                var st="<p> Description= "+va.description+"</p>";
                st+="<p> Temperature= "+va.temp+"</p>";
                st+="<p> Humidity= "+va.humidity+"</p>";
                st+="<p> Speed= "+va.speed+"</p>";     
                st+="<p> Country= "+va.country+"</p>";


                $("#weatherresult").html(st);
            },
            error:function(e){
                console.log(e);
            }
            
        })
        
    })
    
    
})