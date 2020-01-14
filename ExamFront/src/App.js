import React, { Component } from "react";
import {
  BrowserRouter as Router,
  NavLink,
  Route,
  Switch
} from "react-router-dom";
import facade from "./apiFacade";
import DND from "./components/Driver";
import Home from "./components/Home";

class LogIn extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };
  }
  login = evt => {
    evt.preventDefault();
    this.props.login(this.state.username, this.state.password);
  };
  onChange = evt => {
    this.setState({ [evt.target.id]: evt.target.value });
  };
  render() {
    return (
      <div>
        <h2>Login</h2>
        <h3>Enter placeholer values in fields to access site. Or don't. It's all good</h3>
        <form onSubmit={this.login} onChange={this.onChange}>
          <input placeholder="user" id="username" />
          <input placeholder="test" id="password" />
          <button>Login</button>
        </form>
      </div>
    );
  }
}
class LoggedIn extends Component {
  constructor(props) {
    super(props);
    this.state = { dataFromServer: "Fetching!!" };
  }
  componentDidMount() {
    facade.fetchData().then(res => this.setState({ dataFromServer: res.msg }));
  }
  render() {
    return (
      <div>
        <Header />

        <Content />
      </div>
    );
  }
}
/*
function LoggedIn() {
  const [data, setData] = useState({});
  const [id, setId] = useState(1);
  useEffect(() => {
    facade.fetchSpell(id).then(res => setData(res));
    //facade.fetchData().then(res => setData(res));
  }, [id]);

  function set_Id(evt) {
    const id = document.getElementById("id").value;
    setId(id);
  }

  return (
    <div>
      <h2>Data Received from server</h2>
      <div>
        <Header />
        <input id="id" type="text" placeholder="Search" />
        <button onClick={set_Id}> Search </button>
      </div>
      <h3>{JSON.stringify(data)}</h3>
      <Content />
    </div>
  );
}
*/

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false };
  }
  logout = () => {
    facade.logout();
    this.setState({ loggedIn: false });
  };
  login = (user, pass) => {
    facade.login(user, pass).then(res => this.setState({ loggedIn: true }));
  };
  render() {
    return (
      <div>
        {!this.state.loggedIn ? (
          <LogIn login={this.login} />
        ) : (
          <Router>
            <div>
              <LoggedIn />
              <button onClick={this.logout}>Logout</button>
            </div>
          </Router>
        )}
      </div>
    );
  }
}
export default App;

const Header = () => {
  return (
    <ul className="header">
      <li>
        <NavLink exact activeClassName="active" to="/">
          Home
        </NavLink>
      </li>
      <li>
        <NavLink activeClassName="active" to="/Driver">
          Drivers
        </NavLink>
      </li>
    </ul>
  );
};

const Content = () => {
  return (
    <Switch>
      <Route exact path="/">
        {" "}
        <Home />{" "}
      </Route>
      <Route path="/Driver">
        {" "}
        <DND />{" "}
      </Route>
    </Switch>
  );
};
