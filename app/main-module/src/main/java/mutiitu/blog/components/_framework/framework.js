function getAttributes(element, ...attributeNames) {
    const attributes = {};
  
    for (const attributeName of attributeNames) {
      attributes[attributeName] = element.getAttribute(attributeName);
    }
    return attributes;
  }


function _loadCSSFrom(url) {
    return fetch(url)
      .then(response => response.text())
      .then(cssText => {
        const styleSheet = new CSSStyleSheet();
        return styleSheet.replace(cssText);
      })
      .catch(error => {
        console.error('Error al cargar el CSS:', error);
      });
  }



function loadCSSFrom(cssUrls, shadowRoot) {
  
  const cssPromises = cssUrls.map(url => _loadCSSFrom(url));

  Promise.all(cssPromises)
  .then(cssSheets => {
    shadowRoot.adoptedStyleSheets = cssSheets;
  })
  .catch(error => {
    console.error('Error al cargar hojas de estilo:', error);
    console.error('Error al cargar hojas de estilo:', cssUrls);
  });

}




  

export { loadCSSFrom, getAttributes };
