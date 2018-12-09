import React,  {Component} from 'react';
import axios from 'axios';

export default class UserSearchQueries extends Component {
    constructor(props) {
        super(props);
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
        this.getUserSearchQueries = this.getUserSearchQueries.bind(this);
        this.state = {
            userSearchQueries: {}
        }
        this.getUserSearchQueries();
    }

    getUserSearchQueries() {
        let api = process.env.API_URL + '/getUserSearchQueries';
        axios.get(api)
        .then(res => {
            this.setState({userSearchQueries: res.data});
        }).catch(error => {
            console.log(error);
        })
    }

    render() {
        return (
            <div className="container">
            <h5>User Search Queries and Count</h5>
                <ul className="list-group">
                    {Object.keys(this.state.userSearchQueries).map(item=> (
                        <li key={item.toString()} className="list-group-item d-flex justify-content-between align-items-center">
                            {item}
                            <span className="badge badge-primary badge-pill">{this.state.userSearchQueries[item]}</span>
                        </li>
                    ))}
                </ul>
            </div>
        )
    }
}