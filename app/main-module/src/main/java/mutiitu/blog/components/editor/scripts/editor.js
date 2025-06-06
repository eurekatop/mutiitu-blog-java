import { getAttributes } from '/mutiitu/components/_framework/framework.js';
//import monacoEditor from 'https://cdn.jsdelivr.net/npm/monaco-editor@0.44.0/+esm'
import * as monacoEditor from '/mutiitu/libs/systemJS/monaco-editor@0.44.0.js'



export class MuEditor extends HTMLElement { 
      document = undefined

      constructor() {
        super();

        const childElement = document.createElement('div');
        childElement.className = 'monaco';
        childElement.id = 'uniqueID';
        childElement.style.minHeight = '600px';
        this.appendChild(childElement);      
        this.div = childElement
      }
    
      connectedCallback() {
        const attr = getAttributes(this, 'document');
        Object.assign(this, attr);

        console.log ( monacoEditor )
        debugger;

        // editor
        var editor = monacoEditor.editor.create(this.div, 
        {
          value: `# h1 Heading 8-)`,
          language: 'markdown',
//          autoIndent: 'full',
//          contextmenu: true,
            fontFamily: 'monospace',
            fontSize: 13,
//          lineHeight: 24,
//          hideCursorInOverviewRuler: true,
//          matchBrackets: 'always',
            minimap: {
              enabled: false,
            },
//          scrollbar: {
//            horizontalSliderSize: 4,
//            verticalSliderSize: 18,
//          },
//          selectOnLineNumbers: true,
//          roundedSelection: false,
//          readOnly: false,
//          cursorStyle: 'line',
            automaticLayout: false,
//          theme: 'dark',
        });

        editor.onDidChangeModelContent(function (event) {
          var content = editor.getValue();
          console.log("Contenido actual del editor:", content);
        });
      }
  }


customElements.define('mu-editor', MuEditor);
