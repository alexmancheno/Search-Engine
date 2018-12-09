import React,  {Component} from 'react';
import axios from 'axios';

export default class DatabaseInformation extends Component {
    constructor(props) {
        super(props);
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
        this.getDatabaseInformation = this.getDatabaseInformation.bind(this);
        this.state = {
            databaseInformation: {}
        }
        this.getDatabaseInformation();
    }

    getDatabaseInformation() {
        let api = process.env.API_URL + '/getDatabaseInformation';
        axios.get(api)
        .then(res => {
            this.setState({databaseInformation: res.data});
        }).catch(error => {
            console.log(error);
        })
    }

    render() {
        return (
            <div className="container">
            <h5>Database Information</h5>
                <ul className="list-group">
                    {Object.keys(this.state.databaseInformation).map(item=> (
                        <li key={item.toString()} className="list-group-item">
                            {item}: {this.state.databaseInformation[item]}
                        </li>
                    ))}
                </ul>
            </div>
        )
    }
}