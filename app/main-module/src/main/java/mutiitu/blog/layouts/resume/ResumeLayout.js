let codeKeyWordDescriptionsData;
let codeKeyWordsData;
let techSkillsDescriptionsData;
let _loaderDelay = 65;
let _loaderElementsCount = 0;
let _startSceneTake01 = false;

function waitUntilCondition(conditionOperator, executionCallback) {
  const intervalId = setInterval(() => {
    const value = conditionOperator();
    if (value === true) {
      clearInterval(intervalId);
      executionCallback();
    }
  }, 100); // TODO: wait 100 ms
}

const nodes = [];
const nodeTransition = document.getElementById("transition01");

function DEBUG(value) {
  document.getElementById("DEBUG").innerHTML = value + "\n";
}

async function fetchResume(url) {
  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error("No se pudo cargar el archivo JSON.");
    }

    const data = await response.text();
    return data;
  } catch (error) {
    throw error;
  }
}


async function fetchJson(url) {
  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error("No se pudo cargar el archivo JSON.");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    throw error;
  }
}

function hashCode(s) {
  let hash = 0;
  if (s.length === 0) return hash;
  for (let i = 0; i < s.length; i++) {
    const char = s.charCodeAt(i);
    // hash = (hash << 5) - hash + char;
    hash += `${char}`;
  }
  return hash;
}

function delay(func, milliseconds) {
  //setTimeout(() => func(), milliseconds);
  const _func = (_resolve) => {
    func();
    _resolve();
  };
  return new Promise((resolve) => {
    setTimeout(() => _func(resolve), milliseconds);
  });
}

function fireTransition(
  node,
  property,
  srcValue,
  destValue,
  seconds,
  options
) {
  const { beforeCallback, onTransitionEnd } = options || {};
  const _node = node;
  return new Promise((resolve, reject) => {
    _node.addEventListener("transitionend", () => {
      if (onTransitionEnd) onTransitionEnd(_node);
      resolve();
    });

    _node.addEventListener("transitioncancel", () => {
      if (onTransitionEnd) onTransitionEnd(_node);
      resolve();
    });

    setTimeout(() => {
      if (beforeCallback) beforeCallback(_node);

      _node.style.setProperty(property, srcValue);
      _node.style.setProperty("transition", `none`);
      setTimeout(() => {
        _node.style.setProperty("transition", `${property} ${seconds}s`);
        _node.style.setProperty(property, destValue);
      }, 20); // TODO: code smell
    }, 10); // TODO: code smell
  });
}

function getLine(options) {
  const node = document.createElement("div");
  node.classList.add("box1");
  node.style.setProperty("width", 0 + "px");
  nodeTransition.appendChild(node); // TODO: code smell

  return node;
}

function drawLine(node, options) {
  const { x, y, width, time, degrees, color, backgroundImage } = options;
  setTimeout(function () {
    if (time !== undefined)
      node.style.setProperty("transition", `width ${time}s`);
    if (degrees !== undefined)
      node.style.setProperty("transform", `rotate(${degrees}deg)`);
    if (color !== undefined)
      node.style.setProperty("background-color", color);
    if (width !== undefined)
      node.style.setProperty("width", width + "px");
    if (backgroundImage !== undefined)
      node.style.setProperty("background-image", backgroundImage);

    //node.style.setProperty(
    //  "background-image",
    //  "radial-gradient(rgba(0, 150, 0, 0.75), black 120%)"
    //);

    if (y !== undefined) node.style.setProperty("top", y + "px");
    if (x !== undefined) node.style.setProperty("left", x + "px");
  }, 1);
}

/**
 __  __    _    ___ _   _ 
 |  \/  |  / \  |_ _| \ | |
 | |\/| | / _ \  | ||  \| |
 | |  | |/ ___ \ | || |\  |
 |_|  |_/_/   \_\___|_| \_|

*/

let _loaderElementsEnd = 0;
let _loaderElementsPromises = [];
function loader(desc) {
  const screen01 = document.getElementById("screen01");
  _loaderElementsCount++;
  const __L = _loaderElementsCount;
  //const promise = delay(() => {

  window.requestAnimationFrame(() => {
    const h1 = document.createElement("h1");
    nodeTransition.appendChild(h1); // TODO: code smell
    h1.innerText = __L + " - " + desc;
    h1.style.position = "absolute";
    h1.style.marginLeft = "auto";
    h1.style.marginRight = "auto";
    h1.style.textAlign = "center";
    h1.style.border = "1px solid white";
    h1.style.width = "100vw";
    h1.style.display = "none";
    h1.style.fontSize = "4rem";
    h1.style.fontStyle = "Inconsolata";
    h1.style.fontVariant = "monospace";
    h1.style.backgroundColor = "white";
    h1.style.color = "black";

    //fireTranstion(h1, "color", "black", "white", 0.5);
    // fireTranstion(h1, "top", "0vh", "101vh"

    const stack = _loaderElementsPromises.length || 0;
    const promise = fireTransition(
      h1,
      "top",
      `${-stack * 92}vh`,
      "101vh",
      1,
      {
        beforeCallback: (node) => {
          node.style.display = "";
        },
        onTransitionEnd: (node) => {
          //DEBUG(node.innerText);
          nodeTransition.removeChild(node);
        },
      }
    );

    promise
      .then(() => {
        //DEBUG("# promise resolved " + __L);
      })
      .catch((error) => {});
    //}, (__L + 1) * _loaderDelay);
    _loaderElementsPromises.push(promise);
  });
}

async function showKeyWordDescription(keyWord) {
  if ( codeKeyWordDescriptionsData[keyWord]) {
  const popup = new Popup({
    id: "keyWord",
    title: keyWord,
        content: codeKeyWordDescriptionsData[keyWord],
        backgroundColor: "#000",
        titleColor: "#fff",
        textColor: "#fff",
        closeColor: "#fff",
        borderWidth: ".2em",
        borderColor: "#fff",
        linkColor: "#fff",
        fontSizeMultiplier: 1.2,
        titleMargin: "4%",
        underlineLinks: true,
    });      
  popup.show();  
  }
}

function take01_01() {
  // showw content scene
  screen01 = document.getElementById("screen01");
  screen01.style.visibility = "visible";

  delay(() => take01(), 2000);
}

function take01() {
  // get lines
  let color = 0;

  const nodes = [];
  for (let i = 0; i < 105; i++, nodes.push(getLine()));

  const nodes2 = [];
  for (let i = 0; i < 105; i++, nodes2.push(getLine()));

  //////////
  let i = 0;
  let orix = 250; //Math.random() * 500;
  let oriy = Math.random() * 500;
  // get dimensions
  let screen01Height = document.getElementById("screen01").offsetHeight;
  let screen01Width = document.getElementById("screen01").offsetWidth;
  for (var node of nodes) {
    i++;
    drawLine(node, {
      x: orix + i,
      y: -80,
      width: 1000,
      time: 2,
      degrees: 0 + i * 1.5,
      //color: `rgb(${i},255,0)`,
      color: `rgb(${i},${i},${i})`,
      //color: `black`,

      //color: "white",
    });
  }
  //////////
  i = 0;
  for (var node of nodes2) {
    i++;
    drawLine(node, {
      x: 700 + i,
      y: screen01Height + 120,
      width: 1500,
      time: 1,
      degrees: 0 - i * 1.8,
      //color: `rgb(${i},255,0)`,
      //color: `rgb(${i},${i},${i})`,
      color: `black`,
      //color: "white",
    });
  }

  // evento todas las transiciones finito
  let countEndedTransitions = 0;
  const _nodes = nodes.concat(nodes2);
  for (var node of _nodes) {
    node.addEventListener("transitionend", () => {
      countEndedTransitions++;
      if (countEndedTransitions == 210) {
        // TODO: one more code smell
        enTransition(node);
      }
    });
  }

  // end or next
  function enTransition(node) {
    //alert(node);
    const screen = document.getElementById("screen01");
    screen01.innerHTML = /*html*/ `<div class="screen01_title">
          <h1 id="screen01_title"><span>&#8595;</span>FullStack Developer <span>&#8595;</span></h1>
        </div>`;

    for (var node of nodes2) {
      drawLine(node, {
        width: 0,
        time: 1,
      });
    }
    for (var node of nodes) {
      drawLine(node, {
        width: 0,
        time: 1,
      });
    }
    // evento todas las transiciones finito
    let countEndedTransitions = 0;
    const _nodes = nodes.concat(nodes2);
    for (var node of _nodes) {
      node.addEventListener("transitionend", () => {
        countEndedTransitions++;
        if (countEndedTransitions == 210) {
          delay(() => _enTransition(node), 1000); // TODO: CODE SMELL
        }
      });
    }
  }
  // reactive over countEndedTransitions
}

// second fade out
function _enTransition(node) {
  //alert(node);
  const screen = document.getElementById("screen01");

  screen.style.backgroundColor = "black";
  screen.style.color = "white";
  screen01.innerHTML = /*html*/ `<div class="screen01_title">
        <h1 id="screen01_title" style="font-size:20rem">&#8595;&#x1F4BB;&#8595</h1>
      </div>`;

  while (document.querySelectorAll(".box1").length > 0) {
    const boxesToRemove = document.querySelectorAll(".box1");
    boxesToRemove.forEach((box) => box.remove());
  }
}
// reactive over countEndedTransitions

document.addEventListener("DOMContentLoaded", async () => {
  // get markdown resume
  const resumeMd = await fetchResume('https://raw.githubusercontent.com/rfranr/resume/main/resume.md')
  var md = window.markdownit(
    {
      html:         true,        // Enable HTML tags in source
      xhtmlOut:     false,        // Use '/' to close single tags (<br />).
      breaks:       false,        // Convert '\n' in paragraphs into <br>
    }
  );
  var resumeHtml = md.render(resumeMd);

  document.getElementById("lipsum").innerHTML = resumeHtml
  // get code keyWord descriptions
  codeKeyWordDescriptionsData = await fetchJson(
    "/layouts/resume/code-key-word-descriptions.json"
  );
  codeKeyWordsData = await fetchJson("/layouts/resume/code-key-words.json");
  codeKeyWordsData = codeKeyWordsData.sort(function(a, b){return b.length - a.length});
  ;

  techSkillsDescriptionsData = await fetchJson(
    "/layouts/resume/tech-skills-descriptions.json"
  );
  replaceCodeKeyWords();
  //}, 0);

  setTimeout(function () {
    DEBUG(_loaderElementsPromises.length);
    // wait to finish "loader"
    let _local = false;
    if (_loaderElementsPromises.length > 65) {
      DEBUG("pull");
      Promise.all(_loaderElementsPromises)
        .then(() => {
          delay(() => take01_01(), 1000); // TODO: fix promises
        })
        .then(() => {
          _local = true;
        })
        .catch((r) => {
          _local = false;
        });
    }
    //waitUntilCondition(
    //  () => local,
    //  () => {
    //    ///alert("k");
    //    take01_01();
    //  }
    //);
    //take01();
  }, 100);
});

// show content transition
function take02() {
  // abort transition
  firstScroll = true;

  const transition = document.getElementById("transition01");
  transition.style.setProperty("display", "none");

  const screen01 = document.getElementById("screen01");
  const content = document.getElementById("content");
  screen01.style.setProperty("height", "1px");
  content.style.setProperty("top", "1px");

  // add transitions to workExperience elements
  const nodesP = document.querySelectorAll("div.workExperience");
  let nodesPSeconds = 0;
  for (var p of nodesP) {
    nodesPSeconds++;
    fireTransition(
      p,
      "margin-left",
      `-${nodesPSeconds * 1400}px`,
      "0px",
      1.5
    );
  }
  content.style.setProperty("visibility", "visible");
}

// add transitions to code tech
function replaceCodeKeyWords() {
  const content = document.getElementById("lipsum");
  let htmlFragment = content.innerHTML;
  let loaderCount = 0;
  // replace keywords
  for (let keyWord of codeKeyWordsData) {
    const regex = new RegExp(keyWord, "g");
    htmlFragment = htmlFragment.replace(
      regex,
      /*html*/ `${hashCode(keyWord)}`
    );
    //loader(keyWord);
    //loaderCount++;
    //loader(
    //  techSkillsDescriptionsData[
    //    loaderCount % techSkillsDescriptionsData.length
    //  ]
    //);
  }

  for (let keyWord of codeKeyWordsData) {
    const _hashCode = hashCode(keyWord);
    const regex = new RegExp(_hashCode, "g");

    htmlFragment = htmlFragment.replace(
      regex,
      /*html*/
      `<code onclick="showKeyWordDescription('${keyWord}')">${keyWord}</code>`
    );

    loader(keyWord);
  }

  content.innerHTML = htmlFragment;
}

// some fancy on code keywords
function take03() {
  delay(() => {
    const nodesP = document.querySelectorAll("code");
    let nodesPSeconds = 0;
    for (var p of nodesP) {
      nodesPSeconds++;
      fireTransition(p, "background-color", `white`, "black", 0.5);
    }
  }, 200);
}

let firstScroll = undefined;
window.addEventListener("wheel", function (event) {
  // Tu código a ejecutar cuando se desplace la página
  if (!firstScroll) {
    firstScroll = true;
    take02();
    delay(() => {
      take03();
      document.body.style.overflow = "";
    }, 2000);
    document.body.style.overflow = "hidden";
  }
});

window.addEventListener("touchmove", function () {
  if (!firstScroll) {
    firstScroll = true;
    take02();
    delay(() => take03(), 2000);
  }
});
