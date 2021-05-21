
app.filter("unique", function thisFilter() {
  return function(input){
    var seen = { objectNames: [] };
    return input.filter(function(obj){
      return !seen.objectNames.includes(obj.customer.name)
        && seen.objectNames.push(obj.customer.name)
    })
  }
});
