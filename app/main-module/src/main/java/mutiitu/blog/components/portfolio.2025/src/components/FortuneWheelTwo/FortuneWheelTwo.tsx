<!DOCTYPE html>
<html lang="es">
    <title>
        Giro de ruleta con Two.js
    </title>
    <head>
        <style>
            #app {
                width: 800px;
                height: 600px;
                margin: 0 auto;
            }
            #spinButton {
                margin-top: 20px;
            }
            body {
                font-family: Arial, sans-serif;
                text-align: center;
                background-color: black;
            }
        </style>

    </head>
    <body >
        <div id="app"></div>
        <button id="spinButton">Spin the Wheel!</button>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/two.js/0.7.9/two.min.js"></script>
        <script src="path_to_your_script.js"></script>

    </body>

</html>


// Assuming Two.js is already included in your project
const elem = document.getElementById('app');
const two = new Two({ width: 500, height: 500 }).appendTo(elem);

const centerX = 250;
const centerY = 250;
const radius = 200;
const segments = ['Prize 1', 'Prize 2', 'Prize 3', 'Prize 4', 'Prize 5', 'Prize 6', 
  'Prize 7', 'Prize 8', 'Prize 9', 'Prize 10', 'Prize 11', 'Prize 12', 'Prize 13'
];
const segmentAngle = (2 * Math.PI) / segments.length;

let wheel = two.makeGroup();
wheel.translation.set(centerX, centerY); // Set the rotation center

// Function to create an icon or image in each slice
function createIcon(angle, label) {
  const icon = two.makeRectangle(Math.cos(angle) * 120, Math.sin(angle) * 120, 40, 40);
  
  
  icon.fill = '#FFFFFF'; // Placeholder color
  // random color
  icon.fill = '#' + Math.floor(Math.random() * 16777215).toString(16);
  
  icon.stroke = 'black';
  icon.linewidth = 1;
  icon.rotation = angle;
  return icon;
}

// Draw segments
segments.forEach((label, i) => {
  const startAngle = i * segmentAngle;
  const endAngle = startAngle + segmentAngle;

  const shape = two.makeArcSegment(0, 0, 0, radius, startAngle, endAngle); // Adjust to center
  shape.fill = i % 2 === 0 ?  '#101010' : '#404040';  //'#FFD700' : '#FF4500';
  shape.stroke = 'black';
  shape.linewidth = 2;
  wheel.add(shape);

  const angle = startAngle + segmentAngle / 2;
  const icon = createIcon(angle, label);
  wheel.add(icon);
});

// Spin logic
let spinning = false;
let rotation = 0;
let speed = 0;

let delta_acc = 0.00005;
let deceleration = delta_acc; // 0.0001;

function spinWheel() {
  if (spinning) return;
  spinning = true;
  speed = Math.random() * 0.3 + 0.1;
}

// Mouse interaction for setting initial speed
let isDragging = false;
let lastMouseAngle = 0;

function getMouseAngle(event) {
  const rect = elem.getBoundingClientRect();
  const x = event.clientX - rect.left - centerX;
  const y = event.clientY - rect.top - centerY;
  return Math.atan2(y, x);
}

// Apply events to the entire document
function addMouseEvents(target) {
  target.addEventListener('mousedown', (event) => {
    if ( speed != 0 ) 
      return;

    isDragging = true;
    lastMouseAngle = getMouseAngle(event);
  });

  target.addEventListener('mousemove', (event) => {
    if ( speed != 0 ) 
      return;

    if (isDragging) {
      const currentAngle = getMouseAngle(event);
      const angleDelta = currentAngle - lastMouseAngle;
      rotation += angleDelta;
      wheel.rotation = rotation;
      lastMouseAngle = currentAngle;
    }
  });

  target.addEventListener('mouseup', (event) => {
    if ( speed != 0 ) 
      return;


    if (isDragging) {
      isDragging = false;
      const releaseAngle = getMouseAngle(event);
      speed = (releaseAngle - lastMouseAngle) * 5; // Adjust multiplier for desired speed
      spinWheel();
    }
  });
}

addMouseEvents(document); // Apply events to the entire screen

two.bind('update', function (frameCount) {
  if (spinning) {
    rotation += speed;
    speed -= deceleration;

    // deceleration
    deceleration = deceleration + delta_acc / 10;

    if (speed <= 0) {
      //isDragging = false;
      spinning = false;
      speed = 0;

      const finalAngle = (rotation + Math.PI/2) % (2 * Math.PI);
      const winningIndex = Math.floor((2 * Math.PI - finalAngle) / segmentAngle) % segments.length;
      const winningIndex2 = Math.floor(
        (2 * Math.PI - finalAngle ) / segmentAngle) 
        % segments.length;

      console.log(`---------------: ${winningIndex2}`);
      console.log(`The wheel stopped at: ${segments[winningIndex2]}`);
    }

    wheel.rotation = rotation;
  }
}).play();

document.getElementById('spinButton').addEventListener('click', spinWheel);
