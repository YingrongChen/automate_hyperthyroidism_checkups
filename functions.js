function setDate(){
    document.getElementById('date').innerHTML=Date();
}
function closeMe(){
    // Find the element
         x=document.getElementById("demo");
     //Option 1: Change the style attribute directly
         x.style.display="none";
}

function openMe(){
    // Find the element
    x=document.getElementById("demo");
    //Option 1: Change the style attribute directly
    x.style.display="block";
    
    //Option 2: Change the class
    //x.className="open";
}

function showProperties(element){
    document.getElementById('message').innerHTML = element.alt;
}

function hello(){
    var name = prompt("What is your name?")
    if (name.length != 0){
        document.write("Hello " +  name)
    }
    else
    document.write("Feeling shy?")
}

function average(){
    var grades= [2, 5, , , 9, 8, , 8];
    var sum = 0;
    var count = 0;
    if (grades.length > 0){
        for (index = 0; index < grades.length; index++){
            if (grades[index] != undefined){
                sum += grades[index];
            count++;
            }
        }
        document.write(sum/count);
    }
    else
        document.write("Empty Array");
}