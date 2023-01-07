function pregFunction(){
    if(document.getElementById('pregnant').checked){
        document.getElementById('preg').style.display = "block";
    } else{
        document.getElementById('preg').style.display = "none";
    }
}

function medFunction(){
    if(document.getElementById('onMedication').checked){
        document.getElementById('med').style.display = "block";
    } else{
        document.getElementById('med').style.display = "none";
    }
}

function TFTFunction(){
    if(document.getElementById('TFTtest').checked){
        document.getElementById('TFT').style.display = "block";
    } else{
        document.getElementById('TFT').style.display = "none";
    }
}

function Verify(){

}

function average(){
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