import { getAttributes, loadCSSFrom} from '/mutiitu/components/_framework/framework.js';
import * as editor from 'https://cdn.jsdelivr.net/npm/easymde/dist/easymde.min.js'



export class MuMarkdownEditor extends HTMLTextAreaElement { 
      constructor() {
        super();
        loadCSSFrom(['https://cdn.jsdelivr.net/npm/easymde/dist/easymde.min.css'], document)
          this.id ="mdeditor"
        this.editor = new editor.default({
          element: this,
          autoDownloadFontAwesome:true,
          uploadImage:true,
          imageUploadEndpoint:'/admin/upload/image'
        });

      }

      getValue(){
        return this.editor.value();
      }
    

  }


customElements.define('mu-markdown-editor', MuMarkdownEditor, { extends: 'textarea' });
