import { getAttributes } from '/components/_framework/framework.js';
import global_stylesheet from "/css/global-style.css" assert { type: "css" }
import card_stylesheet from "/components/blogpost/css/blog-post.css" assert { type: "css" }


export class BlogPost extends HTMLElement { 
    title = undefined;
    subtitle = undefined;
    content = undefined;

    constructor() {
        super();
        this.attachShadow({ mode: 'open' }).adoptedStyleSheets = [global_stylesheet, card_stylesheet]
      }
    
      connectedCallback() {
        const attr = getAttributes(this, 'title', 'subtitle', 'content');
        Object.assign(this, attr);

        this.shadowRoot.innerHTML = 
        /*html*/
      ` <div class="post">
          <h2 class="title">${this.title}</h2>
          <h2 class="subtitle">${this.subtitle}</h2>
          <div class="content">${this.content}</div>
        </div>`;
      }
  }

customElements.define('mu-blog-post', BlogPost);
