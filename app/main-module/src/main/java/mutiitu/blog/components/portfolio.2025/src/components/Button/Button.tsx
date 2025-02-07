import './Button.scss';  // Import the Button-specific styles

const Button = (
  { label, primary, large } : { label?: string, primary?: string, large?: string}
) => {
  const buttonClass = `button ${primary ? 'button--primary' : ''} ${large ? 'button--large' : ''}`;
  const iconClass = `button__icon ${large ? 'button__icon--large' : ''}`;

  return (
    <button class={buttonClass}>
      <span class={iconClass}></span>
      {label}
    </button>
  );
};

export default Button;