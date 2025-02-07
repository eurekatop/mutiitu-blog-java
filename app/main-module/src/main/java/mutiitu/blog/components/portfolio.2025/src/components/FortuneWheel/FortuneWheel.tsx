import { Application, Assets, Sprite } from "pixi.js";
import { Component, createRef } from "preact";
//import type { Application as PIXI_Application, Assets as PIXI_Assets, Sprite as PIXI_Sprite } from 'pixi.js';


const importWithoutVite = (path: string) => import(/* @vite-ignore */ path);

export default class FortuneWheel extends Component<{title?:string}> {
    divElement = createRef<HTMLDivElement> ()
    canvasElement = createRef<HTMLDivElement> ()

    
    state = {
        launchPixi: false
    }

    setLaunchPixi() {
        this.setState({ launchPixi: true })
    };


    async pixijs() {
        const {launchPixi} = this.state;
        if (!launchPixi) return;

        // 🗝️ This prevents Vite from optimizing pixi.js during build
        
        //const { Application, Assets, Sprite } = await import('pixi.js');
        // Dynamically load Pixi.js from CDN
        //const pixiModule = await import('https://cdn.jsdelivr.net/npm/pixi.js@8.7.3/dist/pixi.min.mjs');
        //const { Application, Assets, Sprite } = pixiModule;

        //const configModule = await importWithoutVite(
        //    import.meta.env.PROD ? 'https://cdn.jsdelivr.net/npm/pixi.js@8.7.3/dist/pixi.min.mjs' : 'pixi.js'
        //);
        //const { Application, Assets, Sprite } = configModule;


        //const pixiModule = async () => {
        //    if ( !import.meta.env.PROD )
        //        return await import('pixi.js');
        //    else
        //        return await import('https://cdn.jsdelivr.net/npm/pixi.js@8.7.3/dist/pixi.min.mjs');
        //}
        //const { Application, Assets, Sprite }: { 
        //    Application: typeof PIXI_Application; 
        //    Assets: typeof PIXI_Assets; 
        //    Sprite: typeof PIXI_Sprite; 
        //} = await pixiModule();

        // Create a new application
        const app = new Application();

        // Initialize the application
        await app.init(
            { 
//                background: '#1099bb', 
                resolution: window.devicePixelRatio,
                autoDensity: true,
                antialias: true,
                powerPreference: 'high-performance',
                autoStart: true,
                clearBeforeRender: true,
                preserveDrawingBuffer: false,
                roundPixels: true,
                //backgroundColor: '#FFFFFF',
                backgroundAlpha: 0
            });

            // Append the application canvas to the document body
        this.canvasElement.current.appendChild(app.canvas);

        // Load the bunny texture
        const texture = await Assets.load('https://pixijs.com/assets/bunny.png');

        // Create a bunny Sprite
        const bunny = new Sprite(texture);

        // Center the sprite's anchor point
        bunny.anchor.set(0.5);

        // Move the sprite to the center of the screen
        bunny.x = app.screen.width / 2;
        bunny.y = app.screen.height / 2;

        app.stage.addChild(bunny);

        // Listen for animate update
        app.ticker.add((time) =>
        {
            // Just for fun, let's rotate mr rabbit a little.
            // * Delta is 1 if running at 100% performance *
            // * Creates frame-independent transformation *
            bunny.rotation += 0.1 * time.deltaTime;
        });

    }

    componentDidMount() {
        console.log("component did mount");
        this.divElement.current.classList.add('animate__animated', 'animate__tada', 'resource__hover');

        // pixijs
        this.pixijs().finally(() => {
            console.log ("PIXI JS ");
        });
    }

    componentDidUpdate() {
        this.pixijs().finally(() => {
            console.log ("PIXI JS (UPDATE)");
        });
    }


    render() {
        return (
            <>
            <button onClick={ () => this.setLaunchPixi()}>Launch</button>
            <div ref={this.divElement}>
<div style="width: 0; height: 0; border-left: 50px solid transparent; 
            border-right: 50px solid transparent; 
            border-top: 50px solid green; margin: 2rem;">
</div>
                
                <p>{"HELLO"}</p>
            </div>
 
            { this.state.launchPixi &&
            <div ref={this.canvasElement} style={{
                zIndex: 1000, // ensure it's always on top of other elements
                display: 'flex', 
                justifyContent: 'center', 
                alignItems: 'center'  // center align text within the container
            }}>
            </div>

            }
            </>

            
        );
    }
}