import React, { Component } from 'react';
// import { Router, Route, browserHistory, hashHistory} from 'react-router';
import { HashRouter, BrowserRouter, Switch, Route } from 'react-router-dom';
import Header from './components/Header';
import UserView from './components/user/UserView';
import AdminView from './components/admin/AdminView';

class App extends Component {
  render() {
    return (
      <HashRouter>
        <div className="container">
          <Header />
          <Switch>
            <Route exact path='/' component={UserView} />
            <Route path='/admin' component={AdminView} />
          </Switch>
            
        </div>
      </HashRouter>
    );
  }
}

export default App;
