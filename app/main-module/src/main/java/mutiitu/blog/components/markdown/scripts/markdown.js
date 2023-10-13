import { getAttributes } from '/components/_framework/framework.js';

import global_stylesheet from "/css/global-style.css" assert { type: "css" }

import global_stylesheet_2 from "https://unpkg.com/basscss@8.1.0/css/basscss.min.css" assert { type: "css" }
import global_stylesheet_3 from "https://cdn.jsdelivr.net/npm/sakura.css/css/sakura.css" assert { type: "css" }


import card_stylesheet from "/components/markdown/css/markdown.css" assert { type: "css" }
import MarkdownIt from 'https://cdn.jsdelivr.net/npm/markdown-it@13.0.2/+esm'

export class MuMarkdown extends HTMLElement { 
    document = undefined;

    constructor() { 
        super();
        this.attachShadow({ mode: 'open' }).adoptedStyleSheets = [  global_stylesheet_3, card_stylesheet]
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
