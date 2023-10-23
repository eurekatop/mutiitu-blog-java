document.process ={
    env : {
      NODE_ENV:"kaka"
    }
  }

window.process ={
    env : {
      NODE_ENV:"kaka"
    }
  }


//import * as React from 'https://cdn.jsdelivr.net/npm/react@18.2.0/umd/react.production.min.js';
import * as React from 'https://cdn.jsdelivr.net/npm/react@18.2.0/umd/react.production.min.js'

class CollapsibleReact extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isCollapsed: false
    };
  }

  toggle = () => {
    this.setState((prevState) => ({
      isCollapsed: !prevState.isCollapsed
    }));
  }

  render() {
    const { isCollapsed } = this.state;
    const { title, children } = this.props;
    return (
      React.createElement('div', { style: { border: 'black dashed 1px' } },
        React.createElement('header', { onClick: this.toggle, style: { backgroundColor: 'blue', color: 'white' } }, title),
        React.createElement('section', { hidden: isCollapsed }, children)
      )
    );
  }
}

CollapsibleReact.defaultProps = {
  title: 'Collapsible Panel'
};

export default CollapsibleReact;
