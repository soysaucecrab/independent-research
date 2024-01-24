const inputForm = document.querySelector("#input-form");
const input = document.querySelector("#input-form input");
const send = document.querySelector(".send-form");
const asciierror = document.querySelector("#input-form p");
const sended = document.querySelector(".send-form p");
const resend = document.querySelector(".resend");
const audioCtx = new (window.AudioContext || window.webkitAudioContext)();

/*
    default settings
*/  

var fast = 100;
var text ="";
var Ascii=[];
var Two=[];
var frequencyList = [1000,3000,5000,7000,9000,11000,13000];
var playList = [[]];


var oscList = [];
var gnList = [];
var osc, gn;
var isPlaying = false;

input.validationMessage = "Write Something";

const HIDDEN_CLASSNAME = "hidden" ;





/*
    InputData
*/

send.classList.add(HIDDEN_CLASSNAME);

function onSubmit(prev){
    prev.preventDefault();
    control();
}

function textInput() {
    text = input.value;
    asciierror.classList.add(HIDDEN_CLASSNAME);
    inputForm.classList.add(HIDDEN_CLASSNAME);
    sended.innerText = `sending : ${text}`;
    send.classList.remove(HIDDEN_CLASSNAME);
}

function reSend(){
    
    input.value = "";
    inputForm.classList.remove(HIDDEN_CLASSNAME);
    send.classList.add(HIDDEN_CLASSNAME);
}

inputForm.addEventListener("submit",onSubmit);
resend.addEventListener("click",reSend);

/*
    convert to Ascii Code
*/
function toAT(){
    for(var i=0;i<text.length;i++){
        
        Ascii[i]=text.charCodeAt(i);
    }
    for(var i=0;i<text.length;i++){
        Two[i]=Ascii[i].toString(2);
        while(Two[i].length<7){
            Two[i] = "0"+Two[i];
        }
    }
}

function clearList(list){
    for(var i=0;i<16;i++){
        list[i]=[];
    }
}

function playlist(j){
    for(var i=0;i<7;i++){
        if(Two[j][i]=="1"){
            playList[j].push(frequencyList[i]);
        }
    }
}

/*
    based sound solution
*/


const playing = document.querySelector(".sound");


function track(frecList) {
  // 이전에 생성된 오실레이터와 게인을 모두 제거
  oscList.forEach(osc => osc.stop());
  gnList.forEach(gn => gn.disconnect());
  oscList = [];
  gnList = [];

  // 주파수 리스트를 순회하면서 오실레이터와 게인을 생성하고 연결
  frecList.forEach(frec => {
    const osc = audioCtx.createOscillator();// generate Oscillator
    osc.type = "sine";// graph type (sine(default), saw, triangle etc)
    osc.frequency.value = frec;

    const gn = audioCtx.createGain();
    osc.connect(gn);
    gn.connect(audioCtx.destination);// connect to speaker

    osc.start(); //start
    oscList.push(osc);
    gnList.push(gn);

    const duration = fast / 5; // 페이드인 지속 시간 (초)
    gn.gain.linearRampToValueAtTime(1, audioCtx.currentTime + duration); // 음량을 점진적으로 증가시킴
  });

  //wait -> fade out
  setTimeout(function () {
    oscList.forEach(osc => {
      const gn = gnList[oscList.indexOf(osc)];
      gn.gain.exponentialRampToValueAtTime(0.0001, audioCtx.currentTime + duration);
      osc.stop(audioCtx.currentTime + duration);
    });
  }, fast);
}

function play(list) {
    
    if (!isPlaying) {
        isPlaying = true;
        audioCtx.resume().then(() => {
            track(list);
            setTimeout(function() {
                isPlaying = false;
            }, fast);
        });
    }
}



function control(){
    //onSubmit()is already
    textInput();
    clearList(Ascii);
    clearList(Two);
    for(var i=0;i<text.length;i++){
        if(text.charCodeAt(i)>128){
            asciierror.classList.remove(HIDDEN_CLASSNAME);
            reSend();
            return;
        }
    }
    toAT();
    clearList(playList);
    for(var i=0;i<text.length;i++){
        playlist(i);
    }
    setTimeout(play(playList[0]),1000);
    
}