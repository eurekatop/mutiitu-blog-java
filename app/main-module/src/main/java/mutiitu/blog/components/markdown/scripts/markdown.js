import { getAttributes, loadCSSFrom } from '/mutiitu/components/_framework/framework.js';

// TODO: ESM modules
//import MarkdownIt from 'https://cdn.jsdelivr.net/npm/markdown-it@13.0.2/+esm'
import * as MarkdownIt from '../libs/node_modules/markdown-it/dist/markdown-it.js'

export class MuMarkdown extends HTMLElement { 
    document = undefined;

    constructor() { 
        super();
        this.attachShadow({ mode: 'open' })
        
        loadCSSFrom(['https://cdn.jsdelivr.net/npm/sakura.css/css/sakura.css',
        '/components/markdown/css/markdown.css',
        'https://cdn.jsdelivr.net/npm/markdown-theme/themes/github.css'
        ], this.shadowRoot)
        
      }
    
      connectedCallback() {
        const attr = getAttributes(this, 'document');
        Object.assign(this, attr);

        const md = MarkdownIt.default()
        const result =  md.render(this.document)

        //TODO: ES6
        //const md = new MarkdownIt();
        //var result = md.render(this.document);
        this.shadowRoot.innerHTML = /*html*/`<div class="markdown-body">${result}</div>`
      }
    }

customElements.define('mu-markdown', MuMarkdown);
