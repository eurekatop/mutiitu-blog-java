export function getAttributes(element, ...attributeNames) {
    const attributes = {};
  
    for (const attributeName of attributeNames) {
      attributes[attributeName] = element.getAttribute(attributeName);
    }
    return attributes;
  }