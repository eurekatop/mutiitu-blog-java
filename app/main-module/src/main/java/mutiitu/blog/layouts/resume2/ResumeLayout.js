System.import('/components/card/scripts/card.js');
System.import('/components/markdown/scripts/markdown.js')  

System.import('/components/gameoflife/scripts/gameoflife.js').then ( (module) => {
    console.log ( module )
    window.module = module;
})
.catch ( (error) => {
    console.log ( error )
});