import React, { Component } from 'react';

export default class SearchResults extends Component {
    constructor(props) {
        super(props);
    }



    render() {
        console.log(this.props.searchResults)
        return (
            <div className="list-group">
                {/* <a href="#" className="list-group-item list-group-item-action active">Cras justo odio</a>
                <a href="#" className="list-group-item list-group-item-action">Dapibus ac facilisis in</a> */}
                {this.props.searchResults.map(result => (
                    <a key={result.toString()} href={result} className="list-group-item list-group-item-action">{result}</a>
                ))}
            </div>
        )
    }
}