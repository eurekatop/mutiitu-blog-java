import { getAttributes } from '/components/_framework/framework.js';
import global_stylesheet from "/css/global-style.css" assert { type: "css" }
import card_stylesheet from "/components/markdown/css/markdown.css" assert { type: "css" }
import MarkdownIt from 'https://cdn.jsdelivr.net/npm/markdown-it@13.0.2/+esm'

export class MuMarkdown extends HTMLElement { 
    document = undefined;

    constructor() { 
        super();
        this.attachShadow({ mode: 'open' }).adoptedStyleSheets = [global_stylesheet, card_stylesheet]
      }
    
      connectedCallback() {
        const attr = getAttributes(this, 'document');
        Object.assign(this, attr);

        const md = new MarkdownIt();

        var result = md.render(this.document);
        this.shadowRoot.innerHTML = result
      }
    }


customElements.define('mu-markdown', MuMarkdown);
