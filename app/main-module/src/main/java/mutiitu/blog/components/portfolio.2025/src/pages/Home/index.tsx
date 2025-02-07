import { text } from 'motion/react-client';
import './style.scss';
import { Component, createRef } from "preact";


export function Home() {

	//const mousePosition = useMousePosition();


	return (
		<div class="home">
			<div class="branding animate__animated animate__rubberBand">
				<div class="__background">
                        <h3>Change color palette <a href="/resume">click here!</a></h3>
				</div>
				<div class="__content">
					<h2>Full Stack Developer</h2>
					<h1>Mutiitu</h1>
				</div>
			</div>

			<section>
			<Resource
					title="Monty Hall Problem Simulation"
					description="Charts and visualization"
					href="/resume"
				/>


				<Resource
					title="Búsqueda Semántica"
					description="Sentence Transformers"
					href="https://preactjs.com/tutorial"
				/>
				<Resource
					title="Differences to React"
					description="If you're coming from React, you may want to check out our docs to see where Preact differs"
					href="https://preactjs.com/guide/v10/differences-to-react"
				/>
				<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>

<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
				<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
				<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
				<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
				<Resource
					title="Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
				<Resource
					title="AA Simplificando el Desarrollo Web con HTMX"
					description="Un Enfoque KISS"
					href="https://vitejs.dev"
				/>
			</section>

		</div>
	);
}

class Resource extends Component<{href:string, title:string, description:string}> {
	divElement = createRef<HTMLDivElement> ()
	eventListener = createRef();
	mouseIn = createRef<boolean>();

	text: string;
	setText: any;
	debounceTime: number;
	startAnimation = createRef<number>();

	constructor() {
		super();
		this.debounceTime = 100;
		this.mouseIn.current = false; // ?
		this.startAnimation.current = 0;
		this.state = {
			text: "this.text",
            setText: "this.setText"
		}
	}

	_mouseIn(e:MouseEvent) {
		console.log('mouseover ' + Math.random(), e.offsetX, e.offsetY, e.clientX, e.clientY);
		console.log(this.divElement.current.offsetWidth, this.divElement.current.offsetHeight);
		console.log(this.divElement.current.offsetTop, this.divElement.current.offsetLeft);
		console.log(this.divElement.current.offsetParent.clientTop, this.divElement.current.clientLeft);
		console.log(this.mouseIn.current);
		console.log(e.currentTarget);
		console.log(e.relatedTarget);
		console.log(e.composed);
		
		

		if ( this.mouseIn.current === false ) {
			// check boundary position of mouse position vs box position
			if (e.offsetX <= 20  || e.offsetX >= this.divElement.current.offsetWidth -20 ||
				e.offsetY <= 20  || e.offsetY >= this.divElement.current.offsetHeight -20) {
				//this.divElement.current.style.border = '1px cyan dashed';
				return;

			}

			//check if class is already added
			if (this.divElement.current.classList.contains('animate__animated')) {
				return;
			}
			this.divElement.current.style.border = '';




			this.divElement.current.classList.add('animate__animated', 'animate__tada', 'resource__hover');
			this.startAnimation.current = 0;
			// Reset text to original state
			//this.setState({ text: this.props.title });

			//const interval = setInterval(() => {
			//	const {current} = this.startAnimation;
			//	if (current <= 0) {
			//		this.divElement.current.classList.remove(  'animate__animated', 'animate__tada');
			//		clearInterval(interval);
			//		this.startAnimation.current = 0;
			//	}
			//	else{
			//		this.startAnimation.current --;
			//	}
			//});

			this.mouseIn.current = true;

		}

	}

	componentDidMount() {
		this.eventListener.current = this.divElement.current.addEventListener('mousemove', (e:MouseEvent) => this._mouseIn(e) );
		this.eventListener.current = this.divElement.current.addEventListener('mouseover', (e:MouseEvent) => this._mouseIn(e) );


        // remove mouse over listener
        this.divElement.current.addEventListener('mouseleave', () => {
			this.mouseIn.current = false;
			console.log('mousevout');
			this.setState({ text: "Mouse Out!" });
			this.divElement.current.classList.remove( 'resource__hover', 'animate__animated', 'animate__tada');
			//this.input.current.classList.remove('animate__animated', 'animate__tada');
        });
	}


	render() {
		return (
			<div class="resource" ref={this.divElement}>
				{this.state.text}
				{ this.startAnimation.current }
				<a>
					<h4>{this.props.title}</h4>
					<p>{this.props.description}</p>
				</a>
			</div>
        );
	}
}