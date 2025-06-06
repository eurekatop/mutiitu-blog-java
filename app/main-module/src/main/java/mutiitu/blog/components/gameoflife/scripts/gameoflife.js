import { getAttributes, loadCSSFrom } from '/mutiitu/components/_framework/framework.js';
//import * as $ from 'https://www.unpkg.com/jquery@3.7.1/dist/jquery.js'
import * as gameOfLife from './qbit-gameoflife.js';


export class MuGameOfLife extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' })
        loadCSSFrom(['/mutiitu/css/global-style.css', 
        '/mutiitu/components/card/css/card.css',
      'https://unpkg.com/@bndynet/dialog/dist/dialog.css'], this.shadowRoot)

        // Agrega un evento 'load' al elemento script que carga jQuery
        const script = document.createElement('script');
        script.src = 'https://code.jquery.com/jquery-3.7.1.min.js';
        const _this = this;
        script.addEventListener('load', () => {
            _this.main()
        });
        document.body.appendChild(script);


      }

      connectedCallback() {
        const { title, subtitle, content } = getAttributes(this, 'title', 'subtitle', 'content');

        this.shadowRoot.innerHTML =
        /*html*/`<div id="container">
        <canvas id="game" width="100" height="100"></canvas>
      </div>` 
      }

      main() {
        const game = gameOfLife.main(this.shadowRoot.getElementById("game"),
        {
          cellsX    : 20,
          cellsY    : 30,
          cellSize  : 8,
          rules     : "23/3",
          gridColor : "grey",
          cellColor : "black"
        })
        
        gameOfLife.run(game)
      }


  }

customElements.define('mu-game-of-life', MuGameOfLife); 


