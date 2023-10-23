


  // Importa React desde un CDN
//import * as React from 'https://cdn.jsdelivr.net/npm/react@18.2.0/+esm'
import * as React from 'https://cdn.jsdelivr.net/npm/react@18.2.0/umd/react.production.min.js';


// Importa ReactDOM desde un CDN
import * as ReactDOM from  'https://cdn.jsdelivr.net/npm/react-dom@18.2.0/index.min.js'

// Importa react-shadow-dom-retarget-events desde un CDN
import * as retargetEvents  from 'https://cdn.jsdelivr.net/npm/react-shadow-dom-retarget-events@1.1.0/index.min.js'

// Importa CollapsibleReact desde un archivo local
import CollapsibleReact from '/components/reactComponent/scripts/collapsiblereact.js';


class MuReactComponent extends HTMLElement {
  constructor() {
    super();
    this.attachShadow({ mode: 'open' });
    this.mountPoint = document.createElement('span');
    this.shadowRoot.appendChild(this.mountPoint);
  }

  static get observedAttributes() {
    return ['title'];
  }

  connectedCallback() {
    const title = this.getAttribute('title');
    this.render(title);
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (name === 'title' && newValue !== oldValue) {
      this.render(newValue);
    }
  }

  render(title) {
    const collapsibleReact = React.createElement(CollapsibleReact, { title }, React.createElement('slot'));
    ReactDOM.render(collapsibleReact, this.mountPoint);
    retargetEvents(this.shadowRoot);
  }
}

customElements.define('mu-react-component', MuReactComponent, { extends: 'textarea' });
