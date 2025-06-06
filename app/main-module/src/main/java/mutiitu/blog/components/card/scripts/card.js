import { getAttributes, loadCSSFrom } from '/mutiitu/components/_framework/framework.js';
import * as dialog from '/mutiitu/components/tmp-components/dialog.js'

export class MuCard extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' })
        loadCSSFrom(['/mutiitu/css/global-style.css', 
        '/mutiitu/components/card/css/card.css',
      'https://unpkg.com/@bndynet/dialog/dist/dialog.css'], this.shadowRoot)
      }

      connectedCallback() {
        const { title, subtitle, content, url, "image-url":imageUrl } = getAttributes(this, 'title', 'subtitle', 'content', 'url' , 'image-url');

        this.shadowRoot.innerHTML =
        /*html*/
      `<div class="card">
        <div class="card-image">
          <img src="${imageUrl}" alt="..">

          <div class="card-content">
          <h3><a href='${url}'>${title}</a></h3>
          <h4>${subtitle}</h4>
          <!-- p>${content}</p -->
        </div>

          </div>
       </div>`;
      }

      alert() {
        dialog.setup({
          theme: "your-theme-3",    // will be appended the `class` attribute of `body` tag, more themes please see https://github.com/bndynet/dialog-themes
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
