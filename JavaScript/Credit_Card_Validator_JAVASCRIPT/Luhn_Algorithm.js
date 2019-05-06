

//creditCardNumber is passed as a string
function isValid(creditCardNumber){

  let strArray = creditCardNumber.split("")

  var numArray = strArray.map(function(v) {
    return parseInt(v, 10);
  });

  let sum = 0

  for(let i = 0; i <= numArray.length - 1; i++){
    if((numArray.length - i - 1) % 2 != 0){

      numArray[i] *= 2
      sum += Math.floor(numArray[i] / 10) + (numArray[i] % 10)
      console.log(i)
    }
    else{
      sum += numArray[i]
    }
  }

  if(sum % 10 === 0){
      return "valid"
    }
    else{
      return "not valid"
    }
}
