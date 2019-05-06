$('button').not('#saveValue').click(function(){
  var previousVal = $('#display').eq(0).val();
  $('#display').val(previousVal + this.innerHTML);
});

$('#\\=').click(function(){ //The \\ in this line is used as escape character'
  var x = $('#display').val();
  var y = x.slice(0,-1);
  $('#display').val(eval(y));
});

$('#AC').click(function(){
  $('#display').val('');
});


var i=0; //variable 'i' goes with the function below
$('#saveValue').click(function(){

  var saveLabel = prompt("Enter Value Label: ");
  var saveValue = $('#display').val();

  $('#saveTable tr td').eq(i).html(saveLabel + ' = ' + saveValue);

  if(i == 5){
    $('#saveTable tr td').eq(i).html('You cannot save more variables. ' +
    'Upgrade to premium to get 50 saved variables at a time');
  }

  i=i+1;

});
