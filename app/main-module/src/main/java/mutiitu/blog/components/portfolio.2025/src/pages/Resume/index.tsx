import { lazy } from 'preact-iso';
import Button from '../../components/Button/Button';
//import { FortuneWheel } from '../../components/FortuneWheel/FortuneWheel';
import ProjectBox from '../../components/Resume/ResumeHeader';
import './style.scss';


const FortuneWheel = lazy (() => import ('../../components/FortuneWheel/FortuneWheel'))

function Resume() {
	return (
		<div class="home">
				<FortuneWheel />
			<h1>Get Started building Vite-powered Preact Apps </h1>
			<section>
			<ProjectBox  description='dsa' link='fdf' techStack={['a', 'b', 'c', 'd', 'e']}  title='dfdfa' />
			</section>
			<section>
				<Button label={"HELLO"} primary='y' />
			</section>

			<section class="project-details">
  <h2>Project Name</h2>
  <p>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas odio, vitae scelerisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Maecenas euismod pharetra nisl, at iaculis risus eleifend nec. Integer sit amet semper nisl. Aenean viverra turpis a nunc volutpat, a sodales turpis maximus.
  </p>
</section>

		</div>
	);
}

function Resource(props) {
	return (
		<a href={props.href} target="_blank" class="resource">
			<h2>{props.title}</h2>
			<p>{props.description}</p>
		</a>
	);
}

export default Resume;
