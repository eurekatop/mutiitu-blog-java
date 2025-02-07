type ProjectProps = {
  title: string;
  description: string;
  techStack: string[];
  link: string;
};

const ProjectBox = ({ title, description, techStack, link }: ProjectProps) => {
  return (
    <div class="project-box">
      <h3 class="project-title">{title}</h3>
      <p class="project-description">{description}</p>
      <div class="tech-stack">
        {techStack.map((tech, index) => (
          <span key={index} class="tech-tag">{tech}</span>
        ))}
      </div>
      <a href={link} target="_blank" rel="noopener noreferrer" class="project-link">
        View Project
      </a>
    </div>
  );
};

export default ProjectBox;
