var publicURL="http://localhost:8888/public/";
{
    getData();
}

$('[type=submit]').click(function(e){
    e.preventDefault();
    if(validateForm()==1){
        addData();
    }   
});

$(document).on('click','[eid]',function(){
    deleteData($(this).attr('eid'));
});

function formToJSON(){
  return JSON.stringify({
      "firstName": $('#firstName').val(),
      "lastName": $('#lastName').val(),
      "age": $('#age').val()
  });
}

function addData(){
                 $.ajax({
                       type:'POST',
                       url: publicURL+'check/save',
                       contentType: "application/json; charset=utf-8",
                       dataType: "json",
                       data:formToJSON()
                 }).done (function(data){

                if(data.status==1){
                    notify("too young");
                }else if(data.status==2){
                    notify("too old");
                }else{
                    render(data.check);
                    notify("Thank you for your information",1);
                }
})
}

function getData(){
                 $.ajax({
                       type:'GET',
                       url: publicURL+'check/all',
                       contentType: "application/json; charset=utf-8",
                       dataType: "json",
                 }).done (function(data){
                  render(data);
});
}

function deleteData(eid){
                 $.ajax({
                       type:'POST',
                       url: publicURL+'check/delete/'+eid,
                       contentType: "application/json; charset=utf-8",
                       dataType: "json"
                 }).done (function(data){
                  notify("Data successfully removed");
                  render(data);
}).fail(function(err,status,xhr){
console.log("AJAX failed: " + JSON.stringify(err, null, 2));
});
}

function render(data){
  var container=$('[cntnr]');
  var containerContent="";
  container.empty();
  $.each(data , function(i,e) {
    containerContent+='<tr><td>'+e.firstName+'</td><td>'+e.lastName+'</td><td>'+e.age+'</td><td> <button type="button" class="btn btn-warning" eid='+e.id+'>Delete</button> </td></tr>';
   });
  container.html(containerContent);
}

function validateForm(){
    var status=1;
    $('input[type=text]').each(function() {
        if($(this).val()==""){
            notify("please fill "+$(this).attr('name')+" field ", 0);
            status=0
        }
   });
    return status;
}

function notify(message,status){
$('.kdnotification-title').html(message);
funcking();
if(status==1){
$('#notification').css({'background-color':'rgba(0,0,0,.4)'}).fadeIn('slow').delay(2000).fadeOut('slow');
}else{
$('#notification').css({'background-color':'rgba(216,0,12,.6)'}).fadeIn('slow').delay(2000).fadeOut('slow');
}
}

function funcking(){
      var kd=$('.kdnotification');
      var viewportHeight = $(window).height(),
          viewportWidth = $(window).width(),
          kdheight = kd.height(),kdwidth = kd.width(),
          hdiff = viewportHeight - kdheight,
          vdiff = viewportWidth - kdwidth,
          left= vdiff/2,
          top = hdiff/2;
      kd.css({'top':top+'px','left':left+'px'});
    }