import { getAttributes, loadCSSFrom } from '/components/_framework/framework.js';
import * as dialog from '/components/tmp-components/dialog.js'

export class MuCard extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' })
        loadCSSFrom(['/css/global-style.css', 
        '/components/card/css/card.css',
      'https://unpkg.com/@bndynet/dialog/dist/dialog.css'], this.shadowRoot)
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

      alert() {
        dialog.setup({
          theme: "your-theme",    // will be appended the `class` attribute of `body` tag, more themes please see https://github.com/bndynet/dialog-themes
          labelOK: "OK",
          labelCancel: "Cancel",
          animate: true,
          notificationAutoClose: true,
          notificationClickClose: true,
          notificationCloseDelay: 3000,
          notificationTheme: "default",
          notificationPlacement: "bottom right",
          notificationMaxItems: 3,
          notificationSquare: false
        });
  
          dialog.setTheme("theme");
  
          dialog.alert("content", function() {});
      }

  }

customElements.define('my-card', MuCard); 
