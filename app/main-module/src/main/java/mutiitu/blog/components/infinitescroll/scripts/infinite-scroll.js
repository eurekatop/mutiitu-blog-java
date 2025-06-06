import { getAttributes, loadCSSFrom } from '/mutiitu/components/_framework/framework.js';
// import global_stylesheet from "/mutiitu/css/global-style.css" // assert { type: "css" }
// import card_stylesheet from "/components/infinitescroll/css/infinite-scroll.css" // assert { type: "css" }


export class InfiniteScroll extends HTMLElement { 
    title = undefined;
    subtitle = undefined;
    content = undefined;

    constructor() {
        super();
        this.attachShadow({ mode: 'open' })
        loadCSSFrom(['/mutiitu/css/global-style.css', '/components/infinitescroll/css/infinite-scroll.css'], this.shadowRoot)
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
          <slot></slot>
        </div>`;
      }
  }

customElements.define('mu-infinite-scroll', InfiniteScroll);
