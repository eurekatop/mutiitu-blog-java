import { getAttributes } from '/components/_framework/framework.js';
import global_stylesheet from "/css/global-style.css" assert { type: "css" };
import card_stylesheet from "/components/card/css/card.css" assert { type: "css" };


export class MuCard extends HTMLElement { 
    constructor() {
        super();
        this.attachShadow({ mode: 'open' }).adoptedStyleSheets = [global_stylesheet, card_stylesheet]
      }
    
      connectedCallback() {
        const { title, subtitle, content } = getAttributes(this, 'title', 'subtitle', 'content');
    

        this.shadowRoot.innerHTML = 
        /*html*/
      `<div class="card">
        <div class="card-image">
          <img src="/images/chrome_niPHyRorSF.png" alt="Imagen de la tarjeta">
        </div>
        <div class="card-content">
          <h3>${title}</h3>
          <h4>${subtitle}</h4>
          <p>${content}</p>
        </div>
       </div>`;
      }
  }
  
customElements.define('my-card', MuCard);
